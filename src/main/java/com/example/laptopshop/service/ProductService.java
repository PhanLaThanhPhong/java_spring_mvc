package com.example.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.Cart;
import com.example.laptopshop.domain.CartDetail;
import com.example.laptopshop.domain.Order;
import com.example.laptopshop.domain.OrderDetail;
import com.example.laptopshop.domain.Product;
import com.example.laptopshop.domain.Product_;
import com.example.laptopshop.domain.User;
import com.example.laptopshop.domain.dto.ProductCriteriaDTO;
import com.example.laptopshop.repository.CartDetailRepository;
import com.example.laptopshop.repository.CartRepository;
import com.example.laptopshop.repository.OrderDetailRepository;
import com.example.laptopshop.repository.OrderRepository;
import com.example.laptopshop.repository.ProductRepository;
import com.example.laptopshop.service.specification.ProductSpecs;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Page<Product> getAllProduct(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductWithSpecs(Pageable pageable, ProductCriteriaDTO productCriteriaDTO){
        Specification<Product> combinedSpecs = Specification.where(null);
        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
            Specification<Product> currentSpecs = ProductSpecs.factoryName(productCriteriaDTO.getFactory().get());
            combinedSpecs = combinedSpecs.and(currentSpecs);
        }
        if (productCriteriaDTO.getTarget() != null && productCriteriaDTO.getTarget().isPresent()){
            Specification<Product> currentSpecs = ProductSpecs.targetName(productCriteriaDTO.getTarget().get());
            combinedSpecs = combinedSpecs.and(currentSpecs);
        }
        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()){
            Specification<Product> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
            combinedSpecs = combinedSpecs.and(currentSpecs);
        }
        return this.productRepository.findAll(combinedSpecs, pageable);
    }

    public Specification<Product> buildPriceSpecification(List<String> price) {
        Specification<Product> combinedSpecs = (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
        double min = 0;
        double max = 0;
        for (String p : price) {
            switch (p) {
                case "duoi-10-trieu":
                    min = 0;
                    max = 10000000;
                    break;
                case "10-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    break;

                case "15-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    break;

                case "tren-20-trieu":
                    min = 20000000;
                    max = Double.MAX_VALUE;
                    break;

                default:
                    break;
            }
            if (min != 0 && max != 0){
                Specification<Product> rangeSpecs = ProductSpecs.rangePrice(min, max);
                combinedSpecs = combinedSpecs.or(rangeSpecs);
            }
        }
        return combinedSpecs;
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteAProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long id, HttpSession session, long quantity) {
        User user = this.userService.getUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            Optional<Product> productOptional = this.productRepository.findById(id);

            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                CartDetail oldCart = this.cartDetailRepository.findByCartAndProduct(cart, product);
                if (oldCart == null) {

                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(product);
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setQuantity(quantity);

                    this.cartDetailRepository.save(cartDetail);

                    int s = cart.getSum() + 1;
                    cart.setSum(cart.getSum() + 1);
                    this.cartRepository.save(cart);

                    session.setAttribute("sum", s);
                } else {
                    oldCart.setQuantity(oldCart.getQuantity() + quantity);
                    this.cartDetailRepository.save(oldCart);
                }
            }
        }
    }

    public void deleteCartDetailById(long id, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(id);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();

            this.cartDetailRepository.deleteById(id);

            if (currentCart.getSum() > 1) {
                int s = currentCart.getSum() - 1;

                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                this.cartRepository.delete(currentCart);
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress,
            String receiverPhone, long totalPrice) {
        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(receiverName);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverPhone(receiverPhone);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");

        order = this.orderRepository.save(order);

        // Get cart by user
        Cart cart = user.getCart();

        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {
                for (CartDetail cartDetail : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setProduct(cartDetail.getProduct());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
            }

            // Step 2: Delete cart va cartDetail
            for (CartDetail cd : cart.getCartDetails()) {
                this.cartDetailRepository.delete(cd);
            }
            this.cartRepository.delete(cart);

            // Step 3: Update session
            session.setAttribute("sum", 0);
        }
    }

    public long countProducts() {
        return this.productRepository.count();
    }
}

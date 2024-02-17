package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String productId) {
        Product product = new Product();
        product.setProductId(productId);

        model.addAttribute("product", product);

        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@ModelAttribute Product product, Model model, @PathVariable("id") String productId) {
        product.setProductId(productId);
        service.edit(product);
        return "redirect:../list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductGet(@ModelAttribute Product product, Model model, @PathVariable("id") String productId) {
        product.setProductId(productId);
        service.delete(product);
        return "redirect:../list";
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "createcar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute("product") Car car, Model model){
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listCarPage(Model model){
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "listCar";
    }

    @GetMapping(value="/editCar/{carId}")
    public String editProductPage(Model model, @PathVariable("carId") String productId){
        Car car = carService.findById(productId);
        model.addAttribute("car", car);
        return "editcar";
    }

    @PostMapping("/editCar")
    public String editProductPost(@ModelAttribute("car") Car car, Model model){
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }
    @PostMapping("/deleteCar")
    public String deleteCar(Model model, @RequestParam("carId") String carId){
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}
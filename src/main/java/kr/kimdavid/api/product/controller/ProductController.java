package kr.kimdavid.api.product.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.kimdavid.api.product.service.ProductService;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.product.domain.ProductDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/test")
    public String test() {
        return "product/test";
    }

    @PostMapping("")
    public String save(ProductDTO products, Model model) {
        Messenger messenger = productService.save(products);
        model.addAttribute("messenger", messenger);
        return "product/save";
    }

    @PostMapping("/all")
    public String saveAll(List<ProductDTO> products, Model model) {
        Messenger messenger = productService.saveAll(products);
        model.addAttribute("messenger", messenger);
        return "product/list";
    }

    @PutMapping("/{id}")
    public String update(ProductDTO products, Model model) {
        Messenger messenger = productService.update(products);
        model.addAttribute("messenger", messenger);
        return "product/list";
    }

    @DeleteMapping("/{id}")
    public String delete(ProductDTO products, Model model) {
        Messenger messenger = productService.delete(products);
        model.addAttribute("messenger", messenger);
        return "product/detail";
    }

    @GetMapping("/id/{id}")
    public String findById(ProductDTO products, Model model) {
        Messenger messenger = productService.findById(products);
        model.addAttribute("messenger", messenger);
        return "product/list";
    }

    @GetMapping({ "", "/", "/all" })
    public String findAll(Model model) {
        List<ProductDTO> products = new ArrayList<>();
        try {
            // CSV 파일에서 상품 데이터 읽기
            ClassPathResource resource = new ClassPathResource("static/csv/products-100.csv");
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : parser) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setIndex(record.get("Index"));
                productDTO.setName(record.get("Name"));
                productDTO.setDescription(record.get("Description"));
                productDTO.setBrand(record.get("Brand"));
                productDTO.setCategory(record.get("Category"));
                productDTO.setPrice(record.get("Price"));
                productDTO.setCurrency(record.get("Currency"));
                productDTO.setStock(record.get("Stock"));
                productDTO.setEan(record.get("EAN"));
                productDTO.setColor(record.get("Color"));
                productDTO.setSize(record.get("Size"));
                productDTO.setAvailability(record.get("Availability"));
                productDTO.setInternalId(record.get("Internal ID"));
                products.add(productDTO);
            }
            parser.close();
            reader.close();

            Messenger messenger = productService.findAll();
            model.addAttribute("messenger", messenger);
            model.addAttribute("products", products);
            return "product/list";
        } catch (IOException e) {
            e.printStackTrace(); // 콘솔에 에러 출력
            Messenger messenger = new Messenger();
            messenger.setCode(1);
            messenger.setMessage("CSV 파일 읽기 오류: " + e.getMessage());
            model.addAttribute("messenger", messenger);
            model.addAttribute("products", new ArrayList<>()); // 빈 리스트 추가
            return "product/list";
        }
    }
}

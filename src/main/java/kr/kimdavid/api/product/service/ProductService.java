package kr.kimdavid.api.product.service;

import java.util.List;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.product.domain.ProductDTO;

public interface ProductService {
    Messenger messenger = new Messenger();
    Messenger save(ProductDTO productDTO);
    Messenger saveAll(List<ProductDTO> productDTOList);
    Messenger update(ProductDTO productDTO);
    Messenger delete(ProductDTO productDTO);
    Messenger findById(ProductDTO productDTO);
    Messenger findAll();

}

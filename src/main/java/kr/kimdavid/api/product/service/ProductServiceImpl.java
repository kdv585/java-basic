package kr.kimdavid.api.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.product.domain.ProductDTO;
import kr.kimdavid.api.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Messenger save(ProductDTO productDTO) {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 등록 성공");
        return messenger;
    }

    @Override
    public Messenger saveAll(List<ProductDTO> productDTOList) {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 일괄 등록 성공");
        return messenger;
    }

    @Override
    public Messenger update(ProductDTO productDTO) {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 수정 성공");
        return messenger;
    }

    @Override
    public Messenger delete(ProductDTO productDTO) {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 삭제 성공");
        return messenger;
    }

    @Override
    public Messenger findById(ProductDTO productDTO) {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 조회 성공");
        return messenger;
    }

    @Override
    public Messenger findAll() {
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("상품 목록 조회 성공");
        return messenger;
    }

}

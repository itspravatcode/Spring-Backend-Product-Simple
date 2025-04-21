package in.itspravat.ecomSite.service;

import in.itspravat.ecomSite.model.Product;
import in.itspravat.ecomSite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }


    public Product getProductById(int id) {
            return repo.findById(id).orElse(null);

    }

    public Product addProduct(Product product, MultipartFile imagefile) throws IOException {
        product.setImageName(imagefile.getOriginalFilename());
        product.setImageType(imagefile.getContentType());
        product.setImageData(imagefile.getBytes());
        return repo.save(product);

    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) {

        product.setImageData(product.getImageData());
        product.setImageName(product.getImageName());
        product.setImageType(product.getImageType());

        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

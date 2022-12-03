package com.infy.ekart.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.entity.Product;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.repository.ProductRepository;
import com.infy.ekart.product.service.CustomerProductServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerProductServiceTest {
	
	@InjectMocks
    CustomerProductServiceImpl customerProductServiceImpl;
    @Mock
    ProductRepository productRepository;

    @Test
    public void getAllProductsTest() throws EKartProductException {
        Product product= new Product();
        product.setAvailableQuantity(150);
        product.setProductId(1);
        product.setCategory("Clothing - Women");
        product.setName("TTL FL ShirtM-38");
        product.setPrice(3500D);
        product.setBrand("LuiPhilipe");
        product.setDescription("LuiPhilipe formal trouser for women / Size-32");

        Product product1= new Product();
        product1.setAvailableQuantity(150);
        product1.setProductId(2);
        product1.setCategory("Clothing - Men");
        product1.setName("Aro STL JeansM-34");
        product1.setPrice(3500D);
        product1.setBrand("ARO");
        product1.setDescription("Skinny Fit stretchable Jeans for Men/Size-32");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> productDTOS = customerProductServiceImpl.getAllProducts();

        Assertions.assertEquals(2,productDTOS.size());
        Assertions.assertTrue(productDTOS.get(0) instanceof ProductDTO);
        Assertions.assertTrue(productDTOS.get(1) instanceof ProductDTO);

        Assertions.assertEquals(150,productDTOS.get(1).getAvailableQuantity());
        Assertions.assertEquals("Clothing - Men",productDTOS.get(1).getCategory());
        Assertions.assertEquals("ARO",productDTOS.get(1).getBrand());
        Assertions.assertEquals(3500D,productDTOS.get(1).getPrice());
        Assertions.assertEquals("Skinny Fit stretchable Jeans for Men/Size-32",productDTOS.get(1).getDescription());
        Assertions.assertEquals(2,productDTOS.get(1).getProductId());
        Assertions.assertEquals("Aro STL JeansM-34",productDTOS.get(1).getName());

        Assertions.assertEquals("TTL FL ShirtM-38",productDTOS.get(0).getName());
        Assertions.assertEquals(150,productDTOS.get(0).getAvailableQuantity());
        Assertions.assertEquals("Clothing - Women",productDTOS.get(0).getCategory());
        Assertions.assertEquals("LuiPhilipe",productDTOS.get(0).getBrand());
        Assertions.assertEquals(3500D,productDTOS.get(0).getPrice());
        Assertions.assertEquals("LuiPhilipe formal trouser for women / Size-32",productDTOS.get(0).getDescription());
        Assertions.assertEquals(1,productDTOS.get(0).getProductId());


    }

    @Test
    public void getProductByIdTest() throws EKartProductException
    {
        Product product= new Product();
        product.setAvailableQuantity(150);
        product.setProductId(1);
        product.setCategory("Clothing - Women");
        product.setName("TTL FL ShirtM-38");
        product.setPrice(3500D);
        product.setBrand("LuiPhilipe");
        product.setDescription("LuiPhilipe formal trouser for women / Size-32");
        Optional<Product> optionalProduct=Optional.of(product);
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);
        ProductDTO productDTO= customerProductServiceImpl.getProductById(product.getProductId());

        Assertions.assertNotNull(productDTO);
        Assertions.assertTrue(productDTO instanceof ProductDTO);
        Assertions.assertEquals(1,productDTO.getProductId());
        Assertions.assertEquals("TTL FL ShirtM-38",productDTO.getName());
        Assertions.assertEquals(150,productDTO.getAvailableQuantity());
        Assertions.assertEquals("Clothing - Women",productDTO.getCategory());
        Assertions.assertEquals("LuiPhilipe",productDTO.getBrand());
        Assertions.assertEquals(3500D,productDTO.getPrice());
        Assertions.assertEquals("LuiPhilipe formal trouser for women / Size-32",productDTO.getDescription());
    }

    @Test
    public void reduceAvailableQuantityTest() throws EKartProductException
    {

        Product product= new Product();
        product.setAvailableQuantity(150);
        product.setProductId(1);
        product.setCategory("Clothing - Women");
        product.setName("TTL FL ShirtM-38");
        product.setPrice(3500D);
        product.setBrand("LuiPhilipe");
        product.setDescription("LuiPhilipe formal trouser for women / Size-32");
        Optional<Product> optionalProduct=Optional.of(product);
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);
        customerProductServiceImpl.reduceAvailableQuantity(product.getProductId(),10);

        Assertions.assertNotNull(product);
        Assertions.assertTrue(product instanceof Product);
        Assertions.assertEquals(1,product.getProductId());
        Assertions.assertEquals("TTL FL ShirtM-38",product.getName());
        Assertions.assertEquals(140,product.getAvailableQuantity());
        Assertions.assertEquals("Clothing - Women",product.getCategory());
        Assertions.assertEquals("LuiPhilipe",product.getBrand());
        Assertions.assertEquals(3500D,product.getPrice());
        Assertions.assertEquals("LuiPhilipe formal trouser for women / Size-32",product.getDescription());
    }
	
}

package com.ran.algoritmovisibilidad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;
import com.ran.algoritmovisibilidad.presenter.service.ProductService;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.presenter.service.StockService;
import com.ran.algoritmovisibilidad.presenter.service.impl.AvailableStockServiceImpl;

class AvailableStockServiceImplTest {

    @Mock
    private ProductService productService;
    
    @Mock
    private SizeService sizeService;
    
    @Mock
    private StockService stockService;
    
    @InjectMocks
    private AvailableStockServiceImpl availableStockService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // database is empty
    @Test
    void testGetAvailableStockEmptyDB() {
        when(productService.getStrictlyRegular()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getRegularStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());

        List<ProductDto> visibleProducts = availableStockService.getVisible();

        assertEquals(0, visibleProducts.size());
        assertTrue(visibleProducts.isEmpty());
    }
    
    // no matching arguments
    @Test
    void testGetAvailableStockNoneMatching() {
        ProductDto product = new ProductDto(1L, 1);
        
        when(productService.hasStock(product.getId())).thenReturn(false);
        when(productService.isBackSoon(product.getId())).thenReturn(false);

        when(productService.getStrictlyRegular()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getRegularStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(0, visibleProducts.size());
        assertTrue(visibleProducts.isEmpty());
    }
    
    // no matching arguments that are backSoon or have stock
    @Test
    void testGetAvailableStockNoneMatchingWithStockOrBackSoon() {
        ProductDto product = new ProductDto(1L, 1);
        
        when(productService.hasStock(product.getId())).thenReturn(false);
        when(productService.isBackSoon(product.getId())).thenReturn(false);

        when(productService.getStrictlyRegular()).thenReturn(Arrays.asList(product));
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getRegularStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(0, visibleProducts.size());
        assertTrue(visibleProducts.isEmpty());
    }
    
    // one of one matching arguments
    @Test
    void testGetAvailableStockOneMatching() {
        ProductDto product = new ProductDto(1L, 1);
        
        when(productService.hasStock(product.getId())).thenReturn(false);
        when(productService.isBackSoon(product.getId())).thenReturn(true);

        when(productService.getStrictlyRegular()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(Arrays.asList(product));
        when(productService.getRegularStockedOrBackSoon()).thenReturn(Arrays.asList(product));
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(1, visibleProducts.size());
        assertFalse(visibleProducts.isEmpty());
    }
    
    // the product is in the special list but not in the regular (and viceversa), hence not following special case 2
    @Test
    void testGetAvailableStockProductHasSpecialAndNoRegular() {
        ProductDto product = new ProductDto(1L, 1);
        
        when(productService.hasStock(product.getId())).thenReturn(false);
        when(productService.isBackSoon(product.getId())).thenReturn(true);

        when(productService.getStrictlyRegular()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(Arrays.asList(product));
        when(productService.getRegularStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(0, visibleProducts.size());
        assertTrue(visibleProducts.isEmpty());
        
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getRegularStockedOrBackSoon()).thenReturn(Arrays.asList(product));
        
        List<ProductDto> visibleProducts2 = availableStockService.getVisible();
        
        assertEquals(0, visibleProducts2.size());
        assertTrue(visibleProducts2.isEmpty());
    }

    // test of the ordering
    @Test
    void testGetAvailableStockOrdering() {
        ProductDto p1 = new ProductDto(1L, 6);
        ProductDto p2 = new ProductDto(2L, 1);
        
        when(productService.hasStock(p1.getId())).thenReturn(false);
        when(productService.isBackSoon(p1.getId())).thenReturn(true);
        when(productService.hasStock(p2.getId())).thenReturn(true);
        when(productService.isBackSoon(p2.getId())).thenReturn(true);

        when(productService.getStrictlyRegular()).thenReturn(Arrays.asList(p1, p2));
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        when(productService.getRegularStockedOrBackSoon()).thenReturn(new ArrayList<ProductDto>());
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(2, visibleProducts.size());
        assertFalse(visibleProducts.isEmpty());
        assertEquals(p2.getId(), visibleProducts.get(0).getId());
        assertEquals(p1.getId(), visibleProducts.get(1).getId());
    }

    // a more complete general test
    @Test
    void testGetAvailableStockFullBothCases() {
        ProductDto p2 = new ProductDto(2L, 1);
        ProductDto p5 = new ProductDto(5L, 2);
        ProductDto p8 = new ProductDto(8L, 3);
        ProductDto p6 = new ProductDto(6L, 4);
        ProductDto p3 = new ProductDto(3L, 5);
        ProductDto p1 = new ProductDto(1L, 6); // no stock no back soon
        ProductDto p9 = new ProductDto(9L, 7);
        ProductDto p7 = new ProductDto(7L, 8);
        ProductDto p4 = new ProductDto(4L, 9);
        
        when(productService.hasStock(p1.getId())).thenReturn(false);
        when(productService.isBackSoon(p1.getId())).thenReturn(false);

        when(productService.hasStock(p2.getId())).thenReturn(false);
        when(productService.isBackSoon(p2.getId())).thenReturn(true);
        
        when(productService.hasStock(p3.getId())).thenReturn(true);
        when(productService.isBackSoon(p3.getId())).thenReturn(false);
        
        when(productService.hasStock(p4.getId())).thenReturn(true);
        when(productService.isBackSoon(p4.getId())).thenReturn(true);
        
        when(productService.hasStock(p5.getId())).thenReturn(true);
        when(productService.isBackSoon(p5.getId())).thenReturn(false);
        
        when(productService.hasStock(p6.getId())).thenReturn(false);
        when(productService.isBackSoon(p6.getId())).thenReturn(true);
        
        when(productService.hasStock(p7.getId())).thenReturn(false);
        when(productService.isBackSoon(p7.getId())).thenReturn(true);
        
        when(productService.hasStock(p8.getId())).thenReturn(true);
        when(productService.isBackSoon(p8.getId())).thenReturn(false);
        
        when(productService.hasStock(p9.getId())).thenReturn(true);
        when(productService.isBackSoon(p9.getId())).thenReturn(false);


        when(productService.getStrictlyRegular()).thenReturn(Arrays.asList(p1, p2, p3));
        when(productService.getSpecialStockedOrBackSoon()).thenReturn(Arrays.asList(p5, p6, p7, p8));
        when(productService.getRegularStockedOrBackSoon()).thenReturn(Arrays.asList(p4, p6, p8, p9));
        
        List<ProductDto> visibleProducts = availableStockService.getVisible();
        
        assertEquals(4, visibleProducts.size());
        assertFalse(visibleProducts.isEmpty());
        // elements inside are (ordered): p2, p8, p6, p3
        assertEquals(visibleProducts.get(0).getId(), p2.getId());
        assertEquals(visibleProducts.get(1).getId(), p8.getId());
        assertEquals(visibleProducts.get(2).getId(), p6.getId());
        assertEquals(visibleProducts.get(3).getId(), p3.getId());
    }
}
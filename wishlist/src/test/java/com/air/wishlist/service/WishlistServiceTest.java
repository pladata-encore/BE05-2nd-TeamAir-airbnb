package com.air.wishlist.service;

import com.air.wishlist.domain.dto.request.WishlistRequest;
import com.air.wishlist.domain.entity.Wishlist;
import com.air.wishlist.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@ActiveProfiles("test")
class WishlistServiceTest {

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private WishlistRepository wishlistRepository;

    @Test
    void addRoomToWishlist() {
    }

    @Test
    void addWishlist() {
        //given
        WishlistRequest request = new WishlistRequest(
                "저장", 123, 1123, "오늘", "내일",
                123, 123, "안녕");

        // when
        wishlistService.addWishlist(request);


        // then
        List<Wishlist> all = wishlistRepository.findAll();
        assertFalse(all.isEmpty());
        List<Wishlist> 저장 = all.stream()
                .filter(el -> el.getName().equals("저장"))
                .toList();
        assertFalse(저장.isEmpty());

    }
    @Test
    void deleteWishlist(){
        //given
        int id = 1;
        wishlistRepository.save(
                Wishlist.builder()
                        .id(id)
                        .name("test")
                        .build()
        );


        //when
        wishlistService.deleteWishlist(id);

        //then
        Optional<Wishlist> byId = wishlistRepository.findById(id);
        Assertions.assertEquals(true, byId.isEmpty());
    }

    @Test
    void deleteRoomToWishlist(){
        //given
        int id = 1;
        wishlistRepository.save(
                Wishlist.builder()
                        .id(id)
                        .name("test")
                        .build()
        );


        //when
        wishlistService.deleteWishlist(id);

        //then
        Optional<Wishlist> byId = wishlistRepository.findById(id);
        assertTrue(byId.isEmpty());
    }
}
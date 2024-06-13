package org.example.springdatajpa.menu.service;

import org.example.springdatajpa.menu.dto.CategoryDTO;
import org.example.springdatajpa.menu.dto.MenuDTO;
import org.example.springdatajpa.menu.entity.Category;
import org.example.springdatajpa.menu.entity.Menu;
import org.example.springdatajpa.menu.repository.CategoryRepository;
import org.example.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*설명.
*  Service 계층: 비즈니스 로직, 트랜젝션 처리(@Transactional), DTO <-> Entity(modelmapper lib 활용)*/
@Service
public class MenuService {

    private final ModelMapper mapper;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MenuService(ModelMapper mapper, MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }

    /*설명 1. findById 예제*/
    // controller와 service 사이 DTO만 이동하므로 Service에서 DTO로 변환해서 controller로
    // repository에서는 엔티티로 
    public MenuDTO findMenuByCode(int menuCode) {
        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return mapper.map(menu, MenuDTO.class);
        // 자동으로 엔티티의 모든 필드를 DTO로 변환(필드명이 동일해야 함) - 비효율적
    }

    /*설명 2. findAll(페이징 처리 전) 예제*/
    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").ascending());

        return menuList.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    /*설명 3. findAll(페이징 처리 후) 예제*/
    public Page<MenuDTO> findMenuList(Pageable pageable) {

        /*설명.
         *  1. 넘어온 pageable에 담긴 페이지 번호를 인덱스 개념으로 바꿔서 인지
         *  2. 한 페이지에 뿌려질 데이터 크기
         *  3. 정렬 기준 */

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(),
                Sort.by("menuCode").descending());

        Page<Menu> menuList = menuRepository.findAll(pageable);

        return menuList.map(menu -> mapper.map(menu, MenuDTO.class));
    }

    public List<MenuDTO> findMenuPrice(int menuPrice) {
        /*설명. 전달받은 가격을 초과하는 메뉴의 목록을 조회하는 메소드*/
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);

        return menuList.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    public List<CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void registMenu(MenuDTO newMenu) {
        menuRepository.save(mapper.map(newMenu, Menu.class));
    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {

        Menu foundMenu = menuRepository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalAccessError::new);
        foundMenu.setMenuName(modifyMenu.getMenuName());
    }

    @Transactional
    public void deleteMenu(int menuCode) {
        menuRepository.deleteById(menuCode);
    }
}

package service;

import model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService {
    Page<Province> findAll(Pageable pageable);

    Province finById(Long id);

    void save(Province province);

    void remove(Long id);

    Page<Province> findAllByNameContaining(String name, Pageable pageable);

}

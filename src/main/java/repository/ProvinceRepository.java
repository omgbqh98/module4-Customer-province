package repository;

import model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinceRepository extends PagingAndSortingRepository<Province,Long> {
    Page<Province> findAllByNameContaining(String name, Pageable pageable);
}

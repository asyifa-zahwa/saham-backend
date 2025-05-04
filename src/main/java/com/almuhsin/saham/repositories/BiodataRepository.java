package main.java.com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Integer> {

    // Ambil semua biodata yang aktif dan belum dihapus (soft delete)
    List<Biodata> findByIsDeletedFalseAndIsActiveTrue();

    // Bisa tambahkan custom query jika perlu
}

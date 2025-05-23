package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almuhsin.saham.entities.Biodata;



@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Integer> {

    // Ambil semua biodata yang aktif dan belum dihapus (soft delete)
    // List<Biodata> findByIsDeletedFalseAndIsActiveTrue();

    // // Bisa tambahkan custom query jika perlu
    // List<Biodata> findByNameContainingAndIsDeletedFalseAndIsActiveTrue(String name);
    // cara lihat apakah email sudah ada atau belum pake query
    boolean existsByEmail(String email);

}

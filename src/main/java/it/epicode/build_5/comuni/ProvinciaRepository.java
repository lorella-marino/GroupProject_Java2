package it.epicode.build_5.comuni;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    public Provincia findByNome(String nome);
}
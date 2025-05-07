package it.epicode.build_5.comuni;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
    public Comune findByNome(String nome);
}
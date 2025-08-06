package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.entities.Fattura;
import TeamOne.BW_ENERGIA.entities.StatoFattura;
import TeamOne.BW_ENERGIA.exceptions.NotFoundException;
import TeamOne.BW_ENERGIA.payloads.FatturaDTO;
import TeamOne.BW_ENERGIA.repositories.ClienteRepository;
import TeamOne.BW_ENERGIA.repositories.FatturaRepository;
import TeamOne.BW_ENERGIA.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatturaService {
    @Autowired
    public FatturaRepository fatturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public List<Fattura> findAll() {
        return fatturaRepository.findAll();
    }

    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return this.fatturaRepository.findAll(pageable);
    }

    public Fattura creaFattura(FatturaDTO payload) {
        Cliente cliente = clienteRepository.findById(payload.clienteId()).orElseThrow(() -> new NotFoundException("Cliente mon trovato!"));

        StatoFattura stato = statoFatturaRepository.findById(payload.statoFatturaId()).orElseThrow(() -> new NotFoundException("Stato fattura non trovato!"));

        Fattura fattura = new Fattura();
        fattura.setData(payload.data());
        fattura.setImporto(payload.importo());
        fattura.setNumero(payload.numero());
        fattura.setStatoFattura(stato);
        fattura.setCliente(cliente);

        return fatturaRepository.save(fattura);
    }

    public Fattura findById(Long id) {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("La fattura con id : " + id + " non è presente!"));
    }

    public Fattura findByIdAndUpdate(Long id, FatturaDTO payload) {
        Cliente cliente = clienteRepository.findById(payload.clienteId()).orElseThrow(() -> new NotFoundException("Cliente mon trovato!"));

        StatoFattura stato = statoFatturaRepository.findById(payload.statoFatturaId()).orElseThrow(() -> new NotFoundException("Stato fattura non trovato!"));
        Fattura fattura = findById(id);
        fattura.setData(payload.data());
        fattura.setImporto(payload.importo());
        fattura.setNumero(payload.numero());
        fattura.setStatoFattura(stato);
        fattura.setCliente(cliente);
        return fatturaRepository.save(fattura);
    }

    public void findByIdAndDelete(Long id) {
        Fattura fattura = findById(id);
        fatturaRepository.delete(fattura);
    }

}

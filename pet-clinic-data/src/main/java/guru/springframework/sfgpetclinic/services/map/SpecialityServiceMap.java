package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialtiesService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return super.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        super.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
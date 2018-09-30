package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {

            // check if pet type is set
            owner.getPets().stream()
                    .map(pet -> Optional.ofNullable(pet.getPetType()))
                    .forEach(petType -> Optional.ofNullable(petType).orElseThrow( () -> new RuntimeException("Pet Type is required in pet")));

            // safe new pet types
            owner.getPets().stream()
                    .filter(pet -> pet.getPetType().getId() == null)
                    .forEach(pet -> pet.setPetType(petTypeService.save(pet.getPetType())));

            // safe new pets
            owner.getPets().stream()
                    .filter(pet -> pet.getId() == null)
                    .forEach(petService::save);

        }
        return super.save(owner);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String name) {
        return null;
    }
}

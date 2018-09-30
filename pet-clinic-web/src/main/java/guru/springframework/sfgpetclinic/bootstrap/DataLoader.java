package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType savedDogType = getPetType("Dog");
        PetType savedCatType = getPetType("Cat");

        Pet rosco = createPet(savedDogType, "Rosco");
        Pet luke = createPet(savedCatType, "Luke");

        Owner michael = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "123456789");
        michael.getPets().add(rosco);
        rosco.setOwner(michael);
        ownerService.save(michael);

        Owner fiona = createOwner("Fiona", "Glenanne", "123 Brickerel", "Miami", "123456789");
        fiona.getPets().add(luke);
        luke.setOwner(fiona);
        ownerService.save(fiona);

        createVet("Sam", "Axe");
        createVet("Jessie", "Porter");
    }

    private Pet createPet(PetType savedDogType, String name) {
        Pet pet = new Pet();
        pet.setPetType(savedDogType);
        pet.setBirthDate(LocalDate.now());
        pet.setName(name);
        return pet;
    }

    private PetType getPetType(String name) {
        PetType dog = new PetType();
        dog.setName(name);
        return petTypeService.save(dog);
    }

    private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        return owner;
    }

    private void createVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetService.save(vet);
    }
}

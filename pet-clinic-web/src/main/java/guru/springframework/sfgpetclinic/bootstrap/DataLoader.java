package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() ==0) {
            loadData();
        }
    }

    private void loadData() {
        PetType savedDogType = getPetType("Dog");
        PetType savedCatType = getPetType("Cat");

        Pet rosco = createPet(savedDogType, "Rosco");
        Pet luke = createPet(savedCatType, "Luke");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner michael = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "123456789");
        michael.getPets().add(rosco);
        rosco.setOwner(michael);
        ownerService.save(michael);

        Owner fiona = createOwner("Fiona", "Glenanne", "123 Brickerel", "Miami", "123456789");
        fiona.getPets().add(luke);
        luke.setOwner(fiona);
        ownerService.save(fiona);

        createVet("Sam", "Axe", savedRadiology);
        createVet("Jessie", "Porter", savedSurgery);

        Visit catVisit = new Visit();
        catVisit.setPet(luke);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Data loaded.");
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

    private void createVet(String firstName, String lastName, Specialty specialty) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.getSpecialities().add(specialty);
        vetService.save(vet);
    }
}

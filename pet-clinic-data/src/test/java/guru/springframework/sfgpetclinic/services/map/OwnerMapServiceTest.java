package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long ID = 1L;
    private final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder()
                .id(ID)
                .lastName(LAST_NAME)
                .build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ID);
        assertEquals(ID, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long ID2 = 2L;
        Owner owner2 = Owner.builder().id(ID2).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(ID2, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ID));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ID);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(LAST_NAME);
        assertNotNull(smith);
        assertEquals(ID,smith.getId());
    }

    @Test
    void findByLastNameNotExisting() {
        Owner foo = ownerMapService.findByLastName("foo");
        assertNull(foo);
    }}
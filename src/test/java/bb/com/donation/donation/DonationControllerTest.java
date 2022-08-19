package bb.com.donation.donation;


import bb.com.donation.controller.PersonController;
import bb.com.donation.dto.person.PersonSaveDTO;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import bb.com.donation.service.impl.PersonServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jayway.restassured.RestAssured.given;

@SpringBootTest
class DonationControllerTest {

    @Autowired
    PersonServiceImp personService;

    @Autowired
    PersonController personController;

//
//    @BeforeAll
//    public void setUp() {
//    }
    @Test
    void saveDonationTest() {
        Person person = personController.save(new PersonSaveDTO ("Igor")).getBody();

        Product product = given ().body (Product.builder ()
                .name ("Produto")
                .build ()).when ().post ("/product").as (Product.class);
        Donation donation = given ().body (Donation.builder ()
                .personOwner (Person.builder ().id (1L).build ())
                .product (Product.builder ().id (1L).build ())
                .build ()).when ().post ("/donation").as (Donation.class);
        Assertions.assertNotNull (donation);
    }

    @Test
    public void test() {


        Donation donation = given ()
                .when ()
                .contentType ("application/json")
                .get ("/donation")
                .then ()
                .statusCode (200)
                .extract ().as (Donation.class);
        System.out.println (donation);
        Assertions.assertEquals (donation.getId (), 1);


    }
}

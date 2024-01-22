package com.pet.proj.tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import step.User;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import java.util.List;
public class UserPage {

    private List<User> users;
    private User firstUser;
    private User createdUser;
    private Integer createdUserId;
        public UserPage(List<User> users) {
            this.users = users;
            if (!users.isEmpty()) {
                this.firstUser = users.get(0);
            }
        }

        public void createUser() {

            Response createResponse = given()
                    .log().all()
                    .contentType("application/json")
                    .body(users)
                    .when()
                    .post("/user/createWithList");
            assertCreateResponse(createResponse);
           // createdUser = mapResponseToUser(createResponse);
        }

        public void getUser() {
            // User firstUser = users.get(0);
            Response getResponse = RestAssured.given()
                    .when()
                    .get("/user/" + firstUser.getUsername());
            assertGetResponse(getResponse);

        }

        public void updateUser(User updatedUser) {

            Response putResponse = RestAssured.given()
                    .log().all()
                    .contentType("application/json")
                    .body(updatedUser)
                    .when()
                    .put("/user/" + firstUser.getUsername());
            assertUpdateResponse(putResponse);

        }

        public void deleteUser() {
           // User firstUser = users.get(0);
            Response deleteResponse = RestAssured.given()
                    .when()
                    .delete("/user/" + firstUser.getUsername());
            assertDeleteResponse(deleteResponse);
        }

        private void assertCreateResponse(Response createResponse) {
            Assert.assertEquals(createResponse.getStatusCode(), 200, "Status code is not as expected");
            String responseBody = createResponse.getBody().asString();
            System.out.println("Response Body: " + responseBody);


        }

        private void assertGetResponse(Response getResponse) {
            Assert.assertEquals(getResponse.getStatusCode(), 200, "Status code is not as expected");
            // Assert.assertTrue(getResponse.getBody().asString().contains(firstUser.getUsername()));
        String responseBody = getResponse.getBody().asString();
        System.out.println("Array" + responseBody);
        }

        private void assertUpdateResponse(Response putResponse) {
            Assert.assertEquals(putResponse.getStatusCode(), 200, "Status code is not as expected");
        //Assert.assertTrue(putResponse.getBody().asString().contains(firstUser.getUsername()));
            String userId = putResponse.jsonPath().getString("id");
            System.out.println("User ID: " + userId);
        String responseBody = putResponse.getBody().asString();
        System.out.println("Array" + responseBody);


        }

        private void assertDeleteResponse(Response deleteResponse) {
            Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Status code is not as expected");
    //  Assert.assertFalse(deleteResponse.getBody().asString().contains(firstUser.getUsername()));
        }
    }



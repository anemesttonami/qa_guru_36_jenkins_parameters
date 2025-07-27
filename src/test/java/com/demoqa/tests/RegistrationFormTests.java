package com.demoqa.tests;

import com.demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;


public class RegistrationFormTests extends BaseRegistrationTest {

    private static PracticeFormPage formPage = new PracticeFormPage();

    @Test
    @Tag("positive")
    @DisplayName("проверяем , что Thanks title отображается")
    public void successfulRegistrationTest() {
        formPage.openStartPage()
                .setFirstName("Victor")
                .setLastName("S")
                .setUserEmail("test@test.com")
                .setGender("Male")
                .setMobileNumber("0123456789")
                .setDateOfBirth("1", "January", "2000")
                .setSubjects("English")
                .setHobbies("Sports")
                .uploadTestPicture()
                .setCurrentAddress("Address")
                .setStateAndCity("Haryana", "Lucknow")
                .submitForm()

                .checkThanksTitle();
    }

    @Test
    @Tag("negative")
    @DisplayName("при неполном кол-ве символов номера телефона подсветка на поле краснеет")
    public void mobileNumberHasLessThan10DigitsTest() {
        formPage.openStartPage()
                .setMobileNumber("012345678")
                .submitForm();

        PracticeFormPage.getUserNumberInput().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Tag("negative")
    @DisplayName("при невыбранном гендере подсветка на radio всех трёх гендеров краснеет")
    public void genderNotSelectedTest() {
        formPage.openStartPage()
                .submitForm();

        PracticeFormPage.getGenderMaleRadio().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        PracticeFormPage.getGenderFemaleRadio().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        PracticeFormPage.getGenderOtherRadio().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
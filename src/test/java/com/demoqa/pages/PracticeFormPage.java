package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class PracticeFormPage {
    private static final SelenideElement
            FIRST_NAME_INPUT = $("#firstName"),
            LAST_NAME_INPUT = $("#lastName"),
            USER_EMAIL_INPUT = $("#userEmail"),
            GENDER_MALE_RADIO = $("label[for=gender-radio-1]"),
            GENDER_FEMALE_RADIO = $("label[for=gender-radio-2]"),
            GENDER_OTHER_RADIO = $("label[for=gender-radio-3]"),
            USER_NUMBER_INPUT = $("#userNumber"),
            DATE_OF_BIRTH_INPUT = $("#dateOfBirthInput"),
            SUBJECTS_INPUT = $("#subjectsContainer input"),
            HOBBIES_SPORTS = $("label[for=hobbies-checkbox-1]"),
            HOBBIES_READING = $("label[for=hobbies-checkbox-2]"),
            HOBBIES_MUSIC = $("label[for=hobbies-checkbox-3]"),
            UPLOAD_PICTURE = $("#uploadPicture"),
            CURRENT_ADDRESS_INPUT = $("#currentAddress"),
            STATE = $("#state"),
            CITY = $("#city"),
            SUBMIT = $("#submit"),
            THANKS_TITLE = $("#example-modal-sizes-title-lg");

    CalendarComponent calendar = new CalendarComponent();

    public static SelenideElement getUserNumberInput() {
        return USER_NUMBER_INPUT;
    }

    public static SelenideElement getGenderMaleRadio() {
        return GENDER_MALE_RADIO;
    }

    public static SelenideElement getGenderFemaleRadio() {
        return GENDER_FEMALE_RADIO;
    }

    public static SelenideElement getGenderOtherRadio() {
        return GENDER_OTHER_RADIO;
    }

    public PracticeFormPage openStartPage() {
        step("открываем главную страницу", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        return this;
    }

    public PracticeFormPage setFirstName(String name) {
        step("вводим имя", () -> {
            FIRST_NAME_INPUT.setValue(name);
        });
        return this;
    }

    public PracticeFormPage setLastName(String name) {
        step("вводим фамилию", () -> {
            LAST_NAME_INPUT.setValue(name);
        });
        return this;
    }

    public PracticeFormPage setUserEmail(String email) {
        step("вводим email", () -> {
            USER_EMAIL_INPUT.setValue(email);
        });
        return this;
    }

    public PracticeFormPage setGender(String gender) {

        switch (gender) {
            case "Male":
                step("выбираем Male radio", () -> {
                    GENDER_MALE_RADIO.click();
                });
                break;
            case "Female":
                step("выбираем Female radio", () -> {
                    GENDER_FEMALE_RADIO.click();
                });
                break;
            case "Other":
                step("выбираем Other radio", () -> {
                    GENDER_OTHER_RADIO.click();
                });
                break;
        }

        return this;
    }

    public PracticeFormPage setMobileNumber(String number) {
        step("вводим номер телефона", () -> {
            USER_NUMBER_INPUT.setValue(number);
        });
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        step("выбираем в календаре дату рождения", () -> {
            DATE_OF_BIRTH_INPUT.click();
            calendar.setDateToCalendar(Integer.valueOf(day), month, year);
        });
        return this;
    }

    public PracticeFormPage setSubjects(String subject) {
        step("выбираем subject", () -> {
            SUBJECTS_INPUT.setValue(subject).sendKeys(Keys.ENTER);
        });
        return this;
    }

    public PracticeFormPage setHobbies(String hobby) {
        if (hobby.equals("Sports")) {
            step("выбираем хобби Sports", () -> {
                HOBBIES_SPORTS.click();
            });
        } else if (hobby.equals("Reading")) {
            step("выбираем хобби Reading", () -> {
                HOBBIES_READING.click();
            });
        } else if (hobby.equals("Music")) {
            step("выбираем хобби Music", () -> {
                HOBBIES_MUSIC.click();
            });
        }
        return this;
    }

    public PracticeFormPage uploadTestPicture() {
        step("грузим картинку", () -> {
            UPLOAD_PICTURE.uploadFromClasspath("image.png");
        });
        return this;
    }

    public PracticeFormPage setCurrentAddress(String address) {
        step("вводим адрес", () -> {
            CURRENT_ADDRESS_INPUT.setValue(address);
        });
        return this;
    }

    public PracticeFormPage setStateAndCity(String state, String city) {
        step("клик по state", () -> {
            this.STATE.click();
        });

        switch (state) {
            case "NCR":
                step("выбираем государство NCR", () -> {
                    $("#react-select-3-option-0").click();
                });
                break;
            case "Uttar Pradesh":
                step("выбираем государство Uttar Pradesh", () -> {
                    $("#react-select-3-option-1").click();
                });
                break;
            case "Haryana":
                step("выбираем государство Haryana", () -> {
                    $("#react-select-3-option-2").click();
                });
                break;
            case "Rajasthan":
                step("выбираем государство Rajasthan", () -> {
                    $("#react-select-3-option-3").click();
                });
                break;
        }

        step("клик по полю выбора города", () -> {
            this.CITY.click();
        });

        switch (city) {
            case "Agra":
                step("выбираем город Agra", () -> {
                    $("#react-select-4-option-0").click();
                });
                break;
            case "Lucknow":
                step("выбираем город Lucknow", () -> {
                    $("#react-select-4-option-1").click();
                });
                break;
            case "Merrut":
                step("выбираем город Merrut", () -> {
                    $("#react-select-4-option-2").click();
                });
                break;
        }

        return this;
    }

    public PracticeFormPage submitForm() {
        step("жмем submit", () -> {
            SUBMIT.click();
        });
        return this;
    }

    public PracticeFormPage checkThanksTitle() {
        step("проверяем наличие заголовка", () -> {
            THANKS_TITLE.shouldHave(exactText("Thanks for submitting the form"));
        });
        return this;
    }
}

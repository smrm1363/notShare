package com.mohammadreza_mirali.tickets4sale.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 09/12/2018.
 */
public class ShowTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    @Test
    public void setTitle() throws Exception {
        Show show = new Show();
        show.setTitle("test");
        assertTrue(show.getTitle().equals("test"));

    }
    @Test
    public void setTitleWrongWay() throws Exception {
        Show show = new Show();
        show.setTitle("");
        Set<ConstraintViolation<Show>> violations
                = validator.validate(show);
        assertTrue(violations.isEmpty());



    }

    @Test
    public void setStartDate() throws Exception {
        Show show = new Show().setStartDate(LocalDate.parse("2017-06-01"));
        assertTrue(show.getStartDate().equals(LocalDate.parse("2017-06-01")));
    }

    @Test
    public void setGenre() throws Exception {
        Show show = new Show().setGenre(GenreEnum.valueOf("MUSICAL"));
        assertTrue(show.getGenreEnum().equals(GenreEnum.MUSICAL));
    }

}
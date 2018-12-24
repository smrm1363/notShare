package com.mohammadreza_mirali.tickets4sale.domain;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
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
public class ShowEntityTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    @Test
    public void setTitle() throws Exception {
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTitle("test");
        assertTrue(showEntity.getTitle().equals("test"));

    }
    @Test
    public void setTitleWrongWay() throws Exception {
        ShowEntity showEntity = new ShowEntity();
        showEntity.setGenreEnum(GenreEnum.COMEDY);
        showEntity.setStartDate(LocalDate.parse("2017-06-01"));
        showEntity.setTitle("");
        Set<ConstraintViolation<ShowEntity>> violations
                = validator.validate(showEntity);
        assertTrue(violations.isEmpty());



    }

    @Test
    public void setStartDate() throws Exception {
        ShowEntity showEntity = new ShowEntity().setStartDate(LocalDate.parse("2017-06-01"));
        assertTrue(showEntity.getStartDate().equals(LocalDate.parse("2017-06-01")));
    }

    @Test
    public void setGenre() throws Exception {
        ShowEntity showEntity = new ShowEntity().setGenreEnum(GenreEnum.valueOf("MUSICAL"));
        assertTrue(showEntity.getGenreEnum().equals(GenreEnum.MUSICAL));
    }

}
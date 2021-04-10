package com.fieryrider.tmdbclone.validation;

import java.lang.reflect.Field;

public abstract class EnumNameBaseValidator {
    protected Enum<?>[] values;

    public void initialize(EnumNameValid constraint) {
        Class<? extends Enum> enumClass = constraint.enumClass();
        try {
            Field f = enumClass.getDeclaredField("$VALUES");
            f.setAccessible(true);
            this.values = (Enum<?>[]) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        /*
        for (Field declaredField : enumClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        */
    }
}

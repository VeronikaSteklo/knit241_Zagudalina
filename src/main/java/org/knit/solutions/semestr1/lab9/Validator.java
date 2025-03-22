package org.knit.solutions.semestr1.lab9;

import org.knit.solutions.semestr1.lab9.Retentions.MaxLength;
import org.knit.solutions.semestr1.lab9.Retentions.Min;
import org.knit.solutions.semestr1.lab9.Retentions.NotNull;

import java.lang.reflect.Field;

public class Validator {
    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> classToValidate = obj.getClass();
        Field[] fields = classToValidate.getDeclaredFields();

        for (Field field : fields) {
            //разрешает доступ к private переменным
            field.setAccessible(true);
            Object value = field.get(obj);

            // Проверка @NotNull
            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                System.out.println("Поле " + field.getName() + " не должно быть null.");
            }

            // Проверка @MaxLength
            if (field.isAnnotationPresent(MaxLength.class)) {
                int maxLength = field.getAnnotation(MaxLength.class).value();
                if (value instanceof String) {
                    String unboxedValue = (String) value;
                    if (unboxedValue != null && maxLength < unboxedValue.length()) {
                        System.out.println("Длина поля " + field.getName() + " должна быть не больше " + maxLength);
                    }
                }
            }

            // Проверка @Min
            if (field.isAnnotationPresent(Min.class)) {
                try {
                    int min = field.getAnnotation(Min.class).value();
                    int unboxedValue = (int) value;
                    if (min > unboxedValue) {
                        System.out.println("Поле " + field.getName() + " должно быть не меньше " + min);
                    }
                } catch (Exception e) {

                }
            }
        }
    }
}

package org.thisrc;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Selectors {

    public List<Selector<String>> conditions() {
        return Stream.of(
                new Selector<>("password ok")
                        .lengthCheck(s -> s.length() > 8)
                        .lowerCaseCheck(s -> s.chars().anyMatch(Character::isLowerCase))
                        .upperCaseCheck(s -> s.chars().anyMatch(Character::isUpperCase))
                        .numberCharacterCheck(s -> s.chars().anyMatch(Character::isDigit))
                        .notOldPasswordCheck(s -> true),

                new Selector<>("too short")
                        .lengthCheck(s -> s.length() < 8),

                new Selector<>("need atleast one number")
                        .numberCharacterCheck(s -> s.chars().noneMatch(Character::isDigit)),

                new Selector<>("need atleast one uppercase character")
                        .upperCaseCheck(s -> s.chars().noneMatch(Character::isUpperCase)),

                new Selector<>("need atleast one lowercase character")
                        .lowerCaseCheck(s -> s.chars().noneMatch(Character::isLowerCase))

        ).collect(Collectors.toList());
    }


    public static class Selector<T> implements Predicate<String> {

        public final T value;
        private Predicate<String> condition = t -> true;

        public Selector(T value) {
            this.value = value;
        }

        public Selector<T> lengthCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }

        public Selector<T> upperCaseCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }

        public Selector<T> lowerCaseCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }

        public Selector<T> numberCharacterCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }


        public Selector<T> specialCharacterCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }

        public Selector<T> notOldPasswordCheck(Predicate<String> pred) {
            condition = condition.and(pred);
            return this;
        }

        @Override
        public boolean test(String t) {
            return condition.test(t);
        }

    }
}

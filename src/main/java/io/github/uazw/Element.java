package io.github.uazw;

public enum Element {

    NONE {
        @Override
        public String toString() {
            return "damage";
        }
    },
    FIRE {
        @Override
        public String toString() {
            return "fire damage";
        }
    }

}

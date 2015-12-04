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
    },
    FROZEN {
        @Override
        public String toString() {
            return "frozen";
        }
    },
    POISON {
        @Override
        public String toString() {
            return "poison";
        }
    },
    PUZZLE {
        @Override
        public String toString() {
            return "puzzle";
        }
    }
}

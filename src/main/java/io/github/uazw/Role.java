package io.github.uazw;

public enum Role {
    SOLDIER {
        @Override
        public boolean isEquipped(Weapon weapon) {
            if (WeaponType.MEDIUM != weapon.getWeaponType()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean isSuitable(Weapon weapon) {
            return isEquipped(weapon) &&
                    weapon.getWeaponType() == WeaponType.MEDIUM;
        }
    }, Knight {
        @Override
        public boolean isEquipped(Weapon weapon) {
            if (WeaponType.MEDIUM == weapon.getWeaponType() ||
                    WeaponType.LARGE == weapon.getWeaponType()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean isSuitable(Weapon weapon) {
            return isEquipped(weapon) &&
                    weapon.getWeaponType() == WeaponType.LARGE;
        }
    }, Assassin {
        @Override
        public boolean isEquipped(Weapon weapon) {
            if (WeaponType.MEDIUM == weapon.getWeaponType() ||
                    WeaponType.SMALL == weapon.getWeaponType()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean isSuitable(Weapon weapon) {
            return isEquipped(weapon) &&
                    weapon.getWeaponType() == WeaponType.SMALL;
        }
    };

    public abstract boolean isEquipped(Weapon weapon);

    public abstract boolean isSuitable(Weapon weapon);
}

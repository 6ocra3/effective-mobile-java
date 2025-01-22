package effective.mobile.tracker.exceptions;

import effective.mobile.tracker.model.role.RoleEnum;

public class NotFoundRoleException extends RuntimeException {
    public <T> NotFoundRoleException(Class<T> clazz, RoleEnum role){
        super("Role with name = " + role.name() + " not found");
    }
}

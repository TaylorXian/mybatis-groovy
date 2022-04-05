package script

class UserSql extends Script {
    @Override
    Object run() {
        return null
    }

    String selectById(Long id) {
        return 'SELECT id, name, age, addr FROM `user` WHERE id = #{id}'
    }
}

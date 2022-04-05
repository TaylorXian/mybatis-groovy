def selectByCondition(Object userVO) {
    '''
SELECT id, name, age, addr
FROM `user`
''' + where { c ->
        if (userVO?.id) c += 'id = #{id} '
        if (userVO?.name) c += ' AND name = #{name} '
        propNotNull(userVO, 'age') { c += ' AND age = #{age} ' }
        c
    }
}

def selectByWrapper(Object parameter) {
    '''
SELECT id, name, age, addr
FROM `user`
WHERE id = ${id} AND name = #{name}
'''
}

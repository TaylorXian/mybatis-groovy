def selectByCondition(Object parameter) {
    '''
SELECT id, name, age, addr
FROM `user`
'''
}

def selectById(Object parameter) {
    '''
SELECT id, name, age, addr
FROM `user`
WHERE id = #{id}
'''
}

def selectByWrapper(Object parameter) {
    '''
SELECT id, name, age, addr
FROM `user`
WHERE id = ${id} AND name = #{name}
'''
}
import request from '@/utils/request'

// 查询教室状态列表
export function listClassrooms(query) {
  return request({
    url: '/ysps/classrooms/list',
    method: 'get',
    params: query
  })
}

// 查询教室状态详细
export function getClassrooms(classroomid) {
  return request({
    url: '/ysps/classrooms/' + classroomid,
    method: 'get'
  })
}

// 新增教室状态
export function addClassrooms(data) {
  return request({
    url: '/ysps/classrooms',
    method: 'post',
    data: data
  })
}

// 修改教室状态
export function updateClassrooms(data) {
  return request({
    url: '/ysps/classrooms',
    method: 'put',
    data: data
  })
}

// 删除教室状态
export function delClassrooms(classroomid) {
  return request({
    url: '/ysps/classrooms/' + classroomid,
    method: 'delete'
  })
}

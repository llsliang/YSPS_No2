import request from '@/utils/request'

// 查询教室预约信息列表
export function listClassroom_reservation(query) {
  return request({
    url: '/ysps/classroom_reservation/list',
    method: 'get',
    params: query
  })
}

// 查询教室预约信息详细
export function getClassroom_reservation(reservationId) {
  return request({
    url: '/ysps/classroom_reservation/' + reservationId,
    method: 'get'
  })
}

// 新增教室预约信息
export function addClassroom_reservation(data) {
  return request({
    url: '/ysps/classroom_reservation',
    method: 'post',
    data: data
  })
}

// 修改教室预约信息
export function updateClassroom_reservation(data) {
  return request({
    url: '/ysps/classroom_reservation',
    method: 'put',
    data: data
  })
}

// 删除教室预约信息
export function delClassroom_reservation(reservationId) {
  return request({
    url: '/ysps/classroom_reservation/' + reservationId,
    method: 'delete'
  })
}

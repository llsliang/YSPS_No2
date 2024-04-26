import request from '@/utils/request'

// 查询参与者信息列表
export function listParticipants(query) {
  return request({
    url: '/ysps/participants/list',
    method: 'get',
    params: query
  })
}

// 查询参与者信息详细
export function getParticipants(participantid) {
  return request({
    url: '/ysps/participants/' + participantid,
    method: 'get'
  })
}

// 新增参与者信息
export function addParticipants(data) {
  return request({
    url: '/ysps/participants',
    method: 'post',
    data: data
  })
}

// 修改参与者信息
export function updateParticipants(data) {
  return request({
    url: '/ysps/participants',
    method: 'put',
    data: data
  })
}

// 删除参与者信息
export function delParticipants(participantid) {
  return request({
    url: '/ysps/participants/' + participantid,
    method: 'delete'
  })
}

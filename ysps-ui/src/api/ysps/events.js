import request from '@/utils/request'

// 查询活动信息列表
export function listEvents(query) {
  return request({
    url: '/ysps/events/list',
    method: 'get',
    params: query
  })
}

// 查询活动信息详细
export function getEvents(eventid) {
  return request({
    url: '/ysps/events/' + eventid,
    method: 'get'
  })
}

// 新增活动信息
export function addEvents(data) {
  return request({
    url: '/ysps/events',
    method: 'post',
    data: data
  })
}

// 修改活动信息
export function updateEvents(data) {
  return request({
    url: '/ysps/events',
    method: 'put',
    data: data
  })
}

// 删除活动信息
export function delEvents(eventid) {
  return request({
    url: '/ysps/events/' + eventid,
    method: 'delete'
  })
}

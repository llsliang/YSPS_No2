import request from '@/utils/request'

// 查询活动评价列表
export function listRatings(query) {
  return request({
    url: '/ysps/ratings/list',
    method: 'get',
    params: query
  })
}

// 查询活动评价详细
export function getRatings(ratingid) {
  return request({
    url: '/ysps/ratings/' + ratingid,
    method: 'get'
  })
}

// 新增活动评价
export function addRatings(data) {
  return request({
    url: '/ysps/ratings',
    method: 'post',
    data: data
  })
}

// 修改活动评价
export function updateRatings(data) {
  return request({
    url: '/ysps/ratings',
    method: 'put',
    data: data
  })
}

// 删除活动评价
export function delRatings(ratingid) {
  return request({
    url: '/ysps/ratings/' + ratingid,
    method: 'delete'
  })
}

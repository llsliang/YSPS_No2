import request from '@/utils/request'

// 查询招聘信息列表
export function listRecruitment(query) {
  return request({
    url: '/ysps/recruitment/list',
    method: 'get',
    params: query
  })
}

// 查询招聘信息详细
export function getRecruitment(jobTitle) {
  return request({
    url: '/ysps/recruitment/' + jobTitle,
    method: 'get'
  })
}

// 新增招聘信息
export function addRecruitment(data) {
  return request({
    url: '/ysps/recruitment',
    method: 'post',
    data: data
  })
}

// 修改招聘信息
export function updateRecruitment(data) {
  return request({
    url: '/ysps/recruitment',
    method: 'put',
    data: data
  })
}

// 删除招聘信息
export function delRecruitment(jobTitle) {
  return request({
    url: '/ysps/recruitment/' + jobTitle,
    method: 'delete'
  })
}

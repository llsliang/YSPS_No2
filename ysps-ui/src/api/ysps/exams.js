import request from '@/utils/request'

// 查询考试信息列表
export function listExams(query) {
  return request({
    url: '/ysps/exams/list',
    method: 'get',
    params: query
  })
}

// 查询考试信息详细
export function getExams(examid) {
  return request({
    url: '/ysps/exams/' + examid,
    method: 'get'
  })
}

// 新增考试信息
export function addExams(data) {
  return request({
    url: '/ysps/exams',
    method: 'post',
    data: data
  })
}

// 修改考试信息
export function updateExams(data) {
  return request({
    url: '/ysps/exams',
    method: 'put',
    data: data
  })
}

// 删除考试信息
export function delExams(examid) {
  return request({
    url: '/ysps/exams/' + examid,
    method: 'delete'
  })
}

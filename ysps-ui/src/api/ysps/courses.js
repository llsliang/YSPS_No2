import request from '@/utils/request'

// 查询包含课程信息，如课程名称、教师、时间和地点列表
export function listCourses(query) {
  return request({
    url: '/ysps/courses/list',
    method: 'get',
    params: query
  })
}

// 查询包含课程信息，如课程名称、教师、时间和地点详细
export function getCourses(courseid) {
  return request({
    url: '/ysps/courses/' + courseid,
    method: 'get'
  })
}

// 新增包含课程信息，如课程名称、教师、时间和地点
export function addCourses(data) {
  return request({
    url: '/ysps/courses',
    method: 'post',
    data: data
  })
}

// 修改包含课程信息，如课程名称、教师、时间和地点
export function updateCourses(data) {
  return request({
    url: '/ysps/courses',
    method: 'put',
    data: data
  })
}

// 删除包含课程信息，如课程名称、教师、时间和地点
export function delCourses(courseid) {
  return request({
    url: '/ysps/courses/' + courseid,
    method: 'delete'
  })
}

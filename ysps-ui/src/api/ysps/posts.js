import request from '@/utils/request'

// 查询论坛列表
export function listPosts(query) {
  return request({
    url: '/ysps/posts/list',
    method: 'get',
    params: query
  })
}

// 查询论坛详细
export function getPosts(postid) {
  return request({
    url: '/ysps/posts/' + postid,
    method: 'get'
  })
}

// 新增论坛
export function addPosts(data) {
  return request({
    url: '/ysps/posts',
    method: 'post',
    data: data
  })
}

// 修改论坛
export function updatePosts(data) {
  return request({
    url: '/ysps/posts',
    method: 'put',
    data: data
  })
}

// 删除论坛
export function delPosts(postid) {
  return request({
    url: '/ysps/posts/' + postid,
    method: 'delete'
  })
}

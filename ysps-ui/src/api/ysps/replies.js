import request from '@/utils/request'

// 查询存储用户对帖子的回复，包括回复内容和时间列表
export function listReplies(query) {
  return request({
    url: '/ysps/replies/list',
    method: 'get',
    params: query
  })
}

// 查询存储用户对帖子的回复，包括回复内容和时间详细
export function getReplies(replyid) {
  return request({
    url: '/ysps/replies/' + replyid,
    method: 'get'
  })
}

// 新增存储用户对帖子的回复，包括回复内容和时间
export function addReplies(data) {
  return request({
    url: '/ysps/replies',
    method: 'post',
    data: data
  })
}

// 修改存储用户对帖子的回复，包括回复内容和时间
export function updateReplies(data) {
  return request({
    url: '/ysps/replies',
    method: 'put',
    data: data
  })
}

// 删除存储用户对帖子的回复，包括回复内容和时间
export function delReplies(replyid) {
  return request({
    url: '/ysps/replies/' + replyid,
    method: 'delete'
  })
}

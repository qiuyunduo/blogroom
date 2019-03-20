import request from '@/utils/request'

export function fashionBlogger(){
  return request({
    url: '/user/user/fashion',
    method: 'get'
  })
}

export function query(data) {
  return request({
    url: '/user/user/query' ,
    method: 'get',
    params: data
  })
}

export function detailUser(userId) {
  return request({
    url: '/user/user/'+userId,
    method: 'get'
  })
}


export function updateUserBaseInfo(data) {
  return request({
    url: '/user/user/updateInfo',
    method: 'post',
    params: data
  })
}

export function updateHeadImage(userId,newImage) {
  return request({
    url: '/user/user/updateHeadImage',
    method: 'post',
    params: { 'userId':userId, 'newImage':newImage}
  })
}

export function updatePwd(data) {
  return request({
    url: '/user/user/updatePwd',
    method: 'post',
    params: data
  })
}

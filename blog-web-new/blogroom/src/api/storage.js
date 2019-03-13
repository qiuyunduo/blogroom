import request from '@/utils/request'

export function uploadFile() {
  return request({
    url: '/website/storage/upload',
    method: 'post'
  })
}

const　uploadPath = process.env.BASE_API + '/website/storage/upload'

export { uploadPath }
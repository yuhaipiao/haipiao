1. Update image

POST /img

body: raw binary data

response:

201
```
{img_id: <long>}
```

2. delete image (support batch)

DELETE /img?[id=<#>,...]

204

3. Create document

POST /doc

body:
```
{
title: <string>,
text_body: <string>,
location: <string>,
user_id: <long>,
tags: <list of string>,
images: [{
  img_id: <long>,
  tags: [{
    name: <string>,
    x: <int/float TBD>,
    y: <int/float TBD>},
  ...
  ]},
  ...
]}
```
201
```
{doc_id: <long>}
```

4. Update document

PUT /doc/{id}

body: same as Create document

200
```
{doc_id: <string>}
```

5. Read document

GET /doc/{id}?request_user

body:
```
{
doc_id: <long>,
title: <string>,
text_body: <string>,
location: <string>,
user_id: <long>,
tags: <list of string>,
images: <list of long>,
likes: <long>,
collects: <long>,
date_updated: <long>,
date_created: <long>
}
```

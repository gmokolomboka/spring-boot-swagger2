Directory layout
---
* public_keys	`public keys of the users`
* dev-servers	`inventory file containing the server URLs`
* README.md	`this file`
* site.yml	`the main playbook file`

Usage
---
`ansible-playbook -vvvv --private-key=C:\glm\sshvmkeys\id_rsa -i dev-servers site.yml`

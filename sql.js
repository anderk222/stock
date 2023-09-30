const fse = require('fs-extra')

const MODULES = {
    ROLE: 'ROLE',
    USER:'USER',
    PROVIDER:'PROVIDER',
    PRODUCT: 'PRODUCT',
    CATEGORY: 'CATEGORY',
    AUTHORITY: 'AUTHORITY',
    PERSON: 'PERSON',
    PRODUCTDETAIL: 'PRODUCTDETAIL',
    SALES: 'SALES',
    PRODUCTSALES: 'PRODUCTSALES'
};

const roles = [
    {
        name : 'ROLE_ADMIN',
        module : ['id']
    },
    {
        name : 'ROLE_USER',
        module : {
            PRODUCT : ['READ'],
            CATEGORY : ['READ'],
            PRODUCTDETAIL : ['READ'],
            PRODUCTSALES : ['READ','WRITE'],
            SALES:['READ','WRITE']
        }
    },
];


function genre(){

    let result ='';

    for(let role of roles){


        if(role.module?.length){

                for(const property in MODULES){
    
                    result+= `INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='${role.name}')
                    ,(SELECT id FROM public.authority WHERE name='${property}_READ'));\n`;
    
                    result+= `INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='${role.name}')
                    ,(SELECT id FROM public.authority WHERE name='${property}_WRITE'));\n`;
    

        }
        }else{

            for(const property in role.module){

                console.log('\n\n\n');
                console.log(role);
                console.log(role.module[property])

                for(let permiso of role.module[property] ){

                    result+= `INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='${role.name}')
                    ,(SELECT id FROM public.authority WHERE name='${property}_${permiso}'));\n`;


                }

            }

        }

    }

    fse.outputFile('./genre.sql', result);

}

genre()
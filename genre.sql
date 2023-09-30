INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='ROLE_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='ROLE_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='USER_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='USER_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PROVIDER_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PROVIDER_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCT_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCT_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='CATEGORY_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='CATEGORY_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='AUTHORITY_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='AUTHORITY_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PERSON_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PERSON_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTDETAIL_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTDETAIL_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='SALES_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='SALES_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTSALES_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_ADMIN')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTSALES_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCT_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='CATEGORY_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTDETAIL_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTSALES_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='PRODUCTSALES_WRITE'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='SALES_READ'));
INSERT INTO role_authority(role_id, authority_id) 
                    VALUES((SELECT id FROM public.role where name='ROLE_USER')
                    ,(SELECT id FROM public.authority WHERE name='SALES_WRITE'));

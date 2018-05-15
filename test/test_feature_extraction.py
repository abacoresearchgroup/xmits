# coding: utf-8
import unittest
import feature

class TestFeatureExtractor(unittest.TestCase):

    def test_feature_extractor(self):

        result = feature.extract_key_words('O cesto contém legal.',"O deste deve era conter.")

        print(result)

        self.assertEquals(result['noun']['cesto'], 1)

        self.assertEquals(result['article']['o'],  1)

        self.assertEquals(result['verb']['contém'], 1)

        self.assertEqual(result['adjective']['legal'], 1)
